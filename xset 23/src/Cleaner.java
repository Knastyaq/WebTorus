public class Cleaner extends Information_employees {
    public Cleaner(String Name,String Last_name , int Age,  String Position , String Special_skills) {
        setValues(Name,Last_name ,Age,Position ,Special_skills  );

    }
    public Cleaner(String change_start,String Job_responsibilities,String Lunch,String change_finish) {
        setValues2(change_start,Job_responsibilities,Lunch,change_finish);

    }
}




//Information_employees Cleaner  = new Information_employees("Оливер","Адамсон",45, "Уборщик", "Опыт работы в гостиницах, заведениях общественного питания, жилых помещениях, офисах, магазинах" );
//        // Cleaner.setValues("Оливер","Адамсон",45, "Уборщик", "Опыт работы в гостиницах, заведениях общественного питания, жилых помещениях, офисах, магазинах"  );
//        String name1 = Cleaner.getValues();
//
//        Information_employees Cleaner1  = new Information_employees("7 AM","Уборка служебных помещений административных зданий, коридоров, лестниц, санузлов, общественных туалетов"," 30 минут","4 PM ");
//       // Cleaner.setValues2("7 AM","Уборка служебных помещений административных зданий, коридоров, лестниц, санузлов, общественных туалетов"," 30 минут","4 PM " );
//        String name1_1 = Cleaner.getValues2();
