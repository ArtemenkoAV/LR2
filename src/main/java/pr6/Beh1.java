package pr6;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//    Поведение начала расчетов:
public class Beh1 extends Behaviour {
    private List<String> AGENTS_receiver = new ArrayList<>(); //список агентов приходит от мейна
    private double x;
    private Random r = new Random();
    private double step;// Шаг расчета
    private boolean Turn = false; //один из критериев завершения поведения
    private ArrayList<Double> Fx_AGENT_1 = new ArrayList<>(),//Значения функций от разных агентов
                              Fx_AGENT_2 = new ArrayList<>(),
                              Fx_AGENT_3 = new ArrayList<>();
    private String Name_Main; //для done
    @Override
    public void onStart() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action() {
            try {
                Thread.sleep(100 + r.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ACLMessage msgR = myAgent.receive(); //"забирает" из пула одно сообщение
            if (msgR != null) {
                System.out.println("----" + myAgent.getLocalName() + " получил ACL от " + msgR.getSender().getLocalName() + " :");
                System.out.println("    getProtocol() " + msgR.getProtocol());
                System.out.println("    getContent()  " + msgR.getContent());
                System.out.println("    getOntology() " + msgR.getOntology());
                if (msgR.getProtocol() == "Function values") { //ACL3

                    //записываем значения функции
                    switch (msgR.getSender().getLocalName()) {
                        case "Agent1":
                            for (String Fx : msgR.getContent().split(" ")) {//split - разделяет строку по выбранному символу(" "). Создается массив
                                Fx_AGENT_1.add(Double.parseDouble(Fx));//parseDouble - из текста в Дабл
                            }
                            break;
                        case "Agent2":
                            for (String Fx : msgR.getContent().split(" ")) {
                                Fx_AGENT_2.add(Double.parseDouble(Fx));
                            }
                            break;
                        case "Agent3":
                            for (String Fx : msgR.getContent().split(" ")) {
                                Fx_AGENT_3.add(Double.parseDouble(Fx));
                            }
                            break;
                    }

                    //смотрим, все ли агенты прислали сообщения, если все, то нужно решить, что дальше делать
                    if (Fx_AGENT_1.size() == 3 && Fx_AGENT_2.size() == 3 && Fx_AGENT_3.size() == 3) {
                        ArrayList<Double> func = new ArrayList<>();// Формирование суммарного массива;
                        for (int i = 0; i < Fx_AGENT_1.size(); i++) {  // суммирует отдельно значения функций от { x - step, x, x + step}
                            func.add(Fx_AGENT_1.get(i) + Fx_AGENT_2.get(i) + Fx_AGENT_3.get(i));
                        }
                        Fx_AGENT_1.clear();//важно очистить, чтобы листы не перепонялись (не было 6 значений, вместо 3)
                        Fx_AGENT_2.clear();
                        Fx_AGENT_3.clear();

                        // Если достаточная точность расчетов достигнута, то завершаем поведение
                        if (step < 0.1) {
                            System.out.println("Максимальное значение функции: " + String.format("%.4f",func.get(1))); //поч 1? А потому что, когда мы в первый(и он же последний) раз прогоняет все функции
                                                                                                //с необходимой точностью, искомое значение х находится посередине диапозона { x - step, x, x + step}
                            System.out.println("Значение Х   : " + String.format("%.4f",x));
                            System.out.println("Значение step: " + String.format("%.4f",step));
                            Turn = true;
                        } else {
                            //изменяет x или step
                            new_X_and_STEP(func);

                            ////Отменяем публикацию сервиса
                            try {
                                DFService.deregister(getAgent());
                            } catch (FIPAException fe) {
                                fe.printStackTrace();
                            }

                            ////передача мейна
                            ACL1();

                            //зачищает значения(для done)
                            step = 0;
                            Name_Main = null;
                        }
                    }
                } else {
                    if (msgR.getProtocol().equals("You main")) {

                        ////публикует свой сервис, по нему все агенту поймут, кому отправлять значения функций
                        DFAgentDescription dfd = new DFAgentDescription();
                        dfd.setName(myAgent.getAID());
                        ServiceDescription sd = new ServiceDescription();
                        sd.setType("ACL1");
                        sd.addProtocols("Main service");
                        sd.setName(myAgent.getLocalName());
                        dfd.addServices(sd);
                        try {
                            DFService.register(myAgent, dfd);
                        } catch (FIPAException fe) {
                            System.out.println("Маин не смог зарегаться!!!!");
                            fe.printStackTrace();
                        }
                        AGENTS_receiver = Arrays.asList(msgR.getOntology().split(" "));
                        Name_Main = myAgent.getLocalName();//для done
                    }

                    ////считает значения функции
                    //asList - Возвращает список фиксированного размера
                    ArrayList<String> X_and_STEP = new ArrayList<>(Arrays.asList(msgR.getContent().split(" ")));//{ x, step}
                    x = Double.parseDouble(X_and_STEP.get(0));
                    step = Double.parseDouble(X_and_STEP.get(1));
                    double[] three_X_values = new double[]{x - step, x, x + step};//{ x - step, x, x + step}

                    ArrayList<Double> Fx_Result = new ArrayList<>();
                    for (double x : three_X_values) {
                        Fx_Result.add(((Agents) getAgent()).calc(x));
                    }
                    System.out.println("Значения функций " + myAgent.getLocalName() + " : " + Fx_Result);

                    ////отправляет сообщение мейну
                    ACL3(Fx_Result);

                    //отправка значений x и step следующему агенту из числа оставшихся(кто ещё не считал)
                    ACL2(msgR.getOntology());
                }
            } else {
                block(); //блокирует работу до тех пор, пока не придет новое сообщение
            }
    }


    @Override
    public int onEnd() { //указывающее на завершение того или иного поведения
        System.out.println(myAgent.getLocalName() + " завершил поведение");
        return 1;
    }

    @Override
    public boolean done() {
        if (step < 0.1 && step != 0 && Name_Main == null){ //если перед нами не мейн, то завершаем поведение при достаточной точности
            Turn = true;
        }
        return Turn;
    }


    //передача мейна. Выбирает рандомного агента из числа всех агентов и отправляет ему сообщение, которое содержит:
    //addReceiver - тот, кто получит сообщение
    //setProtocol - неккий текс, по которому агенты понимают, какую задачу они должны выполнить
    //setContent - тоже текс, хранит в себе значение аргумента функции x и шаг step
    //setOntology - текс, в котором хранится список агентов, которые должны быть задействованы в дальнейших расчетах

    //отправка мейну(агент, который получит все занчения функций) значения x и step
    public void ACL1(){
        int main_random;
        main_random = r.nextInt(AGENTS_receiver.size());
        System.out.println("Передача мейна, теперь мейн : " + AGENTS_receiver.get(main_random));
        ACLMessage ACL1 = new ACLMessage(ACLMessage.INFORM);
        ACL1.addReceiver(new AID(AGENTS_receiver.get(main_random), AID.ISLOCALNAME));  //кто получит это сообщение
        ACL1.setProtocol("You main");
        ACL1.setContent(x + " " + step);

        //создаем строку всех агентов для отправки
        String Agent_str = "";
        for (String agent : AGENTS_receiver){
            if (Agent_str.equals("")){
                Agent_str = agent;
            }
            else {
                Agent_str = Agent_str.concat(" "+ agent);
            }
        }
        ACL1.setOntology(Agent_str);
        myAgent.send(ACL1);  //отправляет ему сообщение
    }

    //отправка x и step случайному агенту, который ещё не считал свою функцию
    public void ACL2(String Ontology){
        ////если остались получатели, то отправляем ACL2
        //удаляем из списка получателей текущего агента
        ArrayList<String> Сurrent_list_receiver = new ArrayList<>(Arrays.asList(Ontology.split(" ")));//{ x, step}
        Сurrent_list_receiver.remove(myAgent.getLocalName());//удаелям из списка получателей текущего агента
        if (Сurrent_list_receiver.size() != 0) {
            //выбираем случайного получателя
            int n = r.nextInt(Сurrent_list_receiver.size());

            //отправляем получателю ACL2(вместе со списоком )
            ACLMessage ACL2 = new ACLMessage(ACLMessage.INFORM);
            ACL2.addReceiver(new AID(Сurrent_list_receiver.get(n), AID.ISLOCALNAME));  //кто получит это сообщение
            ACL2.setProtocol("Function arguments");
            //создаем строку всех агентов для отправки
            String Agent_str = "";
            for (String agent : Сurrent_list_receiver) {
                if (Agent_str.equals("")) {
                    Agent_str = agent;
                } else {
                    Agent_str = Agent_str.concat(" " + agent);
                }
            }
            ACL2.setOntology(Agent_str);
            ACL2.setContent(x + " " + step);
            getAgent().send(ACL2);  //отправляет ему сообщение

        }
    }

    //отправка мейну значений функции
    public void ACL3(ArrayList<Double> Fx_Result){
        //Нужно определить мейна, для этого, обращаемся к DF, и смотрим кто опубликовался как мейн
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.addProtocols("Main service");
        template.addServices(sd);
        try { //ищет определенное сообщение
            DFAgentDescription[] List_mains = DFService.search(myAgent, template); //search поиск. Создается "массив" из агентов
            String Name_Main = String.valueOf(List_mains[0].getName().getLocalName());

            //отправка
            ACLMessage ACL3 = new ACLMessage(ACLMessage.INFORM);
            ACL3.addReceiver(new AID(Name_Main, AID.ISLOCALNAME));  //кто получит это сообщение
            ACL3.setProtocol("Function values");
            ACL3.setContent(Fx_Result.get(0) + " " + Fx_Result.get(1) + " " + Fx_Result.get(2));
            getAgent().send(ACL3);  //отправляет сообщение мейну

        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    //изменяет значения x и step
    public void new_X_and_STEP(ArrayList<Double> func){
        //      Нахождение максимума функции
        double maxFx = func.get(0);
        int idMax = func.indexOf(maxFx); //Возвращает индекс первого вхождения указанного элемента
        for (int i = 0; i < func.size(); i++) {
            if (func.get(i) > maxFx) {
                maxFx = func.get(i);
                idMax = i;
            }
        }

        //Анализ полученых результатов и планирование дальнейших действий
        switch (idMax) {
            case 0:
                x = x - step;
                break;
            case 1:
                step = step / 2;
                break;
            case 2:
                x = x + step;
                break;
        }
    }

}


