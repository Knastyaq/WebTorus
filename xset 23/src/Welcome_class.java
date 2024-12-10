import java.util.Arrays;
import java.util.Scanner;

public class Welcome_class  {
    public static void main(String[] args){
        Cleaner Cleaner = new Cleaner("Оливер","Адамсон",45, "Уборщик", "Опыт работы в гостиницах, заведениях общественного питания, жилых помещениях, офисах, магазинах");
        Cleaner Cleaner1  = new Cleaner("7 AM","Уборка служебных помещений административных зданий, коридоров, лестниц, санузлов, общественных туалетов"," 30 минут","4 PM ");
        Manager Manager  = new Manager("Руби","Брукс",29,"Менеджер","Коммуникативные навыки"+ "," + "Инициативность" + "," + "Нацеленность на результат");
        Manager Manager1 = new Manager("9 AM","Управление коммерческой деятельностью компании, направленной на удовлетворение запросов клиентов и получение прибыли,Организация связей с деловыми партнёрами,Анализ спроса на производимую продукцию или услуги"," 60 минут","6 PM ");
        Security Security  = new Security("Оскар","Буш",36,"Охранник","высокая степень" + "," + " сосредоточенности, внимательности и быстрой реакции" + "," + "пунктуальности и ответственности");
        Security Security1 = new Security( "7 AM","проверять целостность объекта, замков и других запорных устройств, наличие пломб, противопожарного инвентаря, исправность сигнализации, телефонов, освещения, совершать наружный и внутренний обход объекта от трёх раз за смену"," 20 минут","9 PM");
        Driver Driver = new Driver("Джейкоб","Говард",34,"Водитель","возможность определить оптимальный маршрут");
        Driver Driver1 = new Driver("8 AM","Обеспечивать безопасность пассажиров,Обеспечивать сохранность имущества,Контролировать сохранность и целостность автомобиля"," 40 минут","6 PM ") ;

        Scanner console = new Scanner(System.in);
        System.out.println("Привет! напиши номер сотрудника который тебя интересует.");
        System.out.println("1. Уборщик  2. Водитель  3. Менеджер 4. Охраник");
        System.out.println("Ответ: ");
        int number = console.nextInt();
        System.out.println("Какая информация тебе нужна ? распорядок дня или данные о сотруднике?");
        System.out.println("Для получения информации о сотруднике напиши 1"+".\n"+"Для получения распорядка дня напиши 2"+".\n" + "Для получения общей информации напиши 3");
        int number1 = console.nextInt();

        if (number == 1 && number1 == 1){
            System.out.println(Cleaner.getValues());
        } else if (number == 1 && number1 == 2) {
            System.out.println(Cleaner1.getValues2());
        } else if (number == 1 && number1 == 3) {
            System.out.println(Cleaner.getValues());
            System.out.println(Cleaner1.getValues2());
        }else if (number == 2 && number1 == 1) {
            System.out.println(Driver.getValues());
        } else if (number == 2 && number1 == 2) {
            System.out.println(Driver1.getValues2());
        }else if (number == 2 && number1 == 3) {
            System.out.println(Driver.getValues());
            System.out.println(Driver1.getValues2());
        } else if (number == 3 && number1 == 1) {
            System.out.println(Manager.getValues());
        }else if (number == 3 && number1 == 2) {
            System.out.println(Manager1.getValues2());
        } else if (number == 3 && number1 == 3) {
            System.out.println(Manager.getValues());
            System.out.println(Manager1.getValues2());
        }else if (number == 4 && number1 == 1) {
            System.out.println(Security.getValues());
        } else if (number == 4 && number1 == 2) {
            System.out.println(Security1.getValues2());
        }else if (number == 4 && number1 == 3) {
            System.out.println(Security.getValues());
            System.out.println(Security1.getValues2());
        } else {
            System.out.println("Упс!Вы ввели другое значение, попробуйте снова");
        }


//        switch (number) {
//            case 1 :
//                System.out.println(Cleaner.getValues());
//                System.out.println(Cleaner1.getValues2());
//                break;
//            case 2 :
//                System.out.println(Driver.getValues());
//                System.out.println(Driver1.getValues2());
//                break;
//            case 3 :
//                System.out.println(Manager.getValues());
//                System.out.println(Manager1.getValues2());
//                break;
//            case 4 :
//                System.out.println(Security.getValues());
//                System.out.println(Security1.getValues2());
//                break;

        //}


    }


}
