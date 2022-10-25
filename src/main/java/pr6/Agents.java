package pr6;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Основная концепция программы:
 * Имеется три агента, с запущенными поведениями, которые ждут новое сообщение
 * Все агенты запускают одно универсальное поведение, что позволят им легко передавать инициативу
 * По входящему сообщению агенты понимают, какой функционал они должны выполнить
 *  Имеется три типа сообщений. Как реагирует агент:
 *      ACL1 (протокол "You main"), агент получает x, step и список всех агентов принимающих участие в расчетах
 *          Публикует сервис DF, чтобы другие агенты поняли, кто проводит итоговый расчет и анализ
 *          Считает значения своей функции и отправляет мейну(себе) ACL3 (сделано специально, чтобы
 *              объединить схожий функционал ACL1 и ACL2)
 *          Отправляет следующему агенту(рандомно выбранному из числа оставшихся) ACL2
 *
 *      ACL2 (протокол "Function arguments") агент получает x, step и список всех агентов ещё не принивших участие в расчетах
 *          Считает значения своей функции и отправляет мейну ACL3
 *          Отправляет следующему агенту(рандомно выбранному из числа оставшихся) ACL2
 *
 *      ACL3 (протокол "Function values") меин получает значения функций
 *          Записывает значения функций всех агентов
 *          Если все прислали значения своих функций, то формирует суммарный массив и определят, как нужно изменить x и step
 *          Если точность достаточна, то завершает поведение
 *
 */



public class Agents extends Agent {

    private List<String> AGENTS = Arrays.asList("Agent1", "Agent2", "Agent3"); //asList - Возвращает список фиксированного размера
    private Random r = new Random();

    @Override
    protected void setup() { //от сюда стартуют все агенты
        double x = (Math.random()*1); //аргумент функции
        double step = 0.5;  //шаг

        //запускаем у всех агентов поведение
        addBehaviour(new Beh1());

        //пусть агент1 отправит какому-то рандомнуму агенту сообщение, которое запустит цепочку расчетов
        if (getLocalName().equals(AGENTS.get(0))) {
            ACL1(x, step);
        }
    }

    //передача мейна. Выбирает рандомного агента из числа всех агентов и отправляет ему сообщение, которое содержит:
    //addReceiver - тот, кто получит сообщение
    //setProtocol - неккий текс, по которому агенты понимают, какую задачу они должны выполнить
    //setContent - тоже текс, хранит в себе значение аргумента функции x и шаг step
    //setOntology - текс, в котором хранится список агентов, которые должны быть задействованы в дальнейших расчетах
    public void ACL1(double x, double step){
        int main_random;
        main_random = r.nextInt(AGENTS.size());
        System.out.println("Передача мейна, теперь мейн : " + AGENTS.get(main_random));
        ACLMessage ACL1 = new ACLMessage(ACLMessage.INFORM);
        ACL1.addReceiver(new AID(AGENTS.get(main_random), AID.ISLOCALNAME));
        ACL1.setProtocol("You main");
        ACL1.setContent(x + " " + step);

        //создаем строку всех агентов для отправки
        String Agent_str = "";
        for (String agent : AGENTS){
            if (Agent_str.equals("")){
                Agent_str = agent;
            }
            else {
                Agent_str = Agent_str.concat(" "+ agent);//concat - складывает строку (Agent_str) со строкой (" "+ agent)
            }
        }
        ACL1.setOntology(Agent_str);
        send(ACL1);  //отправляет ему сообщение
    }

    public double calc(double x){
        switch (getLocalName()){
            case "Agent1":
                return -0.5*x*x-4;
            case "Agent2":
                return Math.pow(2, -0.1*x);
            case "Agent3":
                return Math.cos(x);
        }
        return x;
    }

}

