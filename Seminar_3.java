import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Seminar_3{
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        
       data();
    }


    public static void data() {
        System.out.println("Введите через пробел Ваши данные Фамилия Имя Отчество, дата рождения в формате дд.мм.гггг, номер телефона (слитно), пол в формате f(женский) или m(мужской): ");
        String dataUser = scanner.nextLine();
        String[] arrayData = dataUser.split(" ");

        if(arrayData.length > 6){
            throw new CountDataException("Не правильное количество введенных данных, возможно вы ввели больше.");
        }
        if(arrayData.length < 6){
            throw new CountDataException("Не правильное количество введенных данных, возможно вы ввели меньше.");
        }
        
        String lastName = null;
        String firstName = null;
        String patronymic = null;
        String date = null;
        String gender = null;
        String tel = null;
        BigDecimal telephone = new BigDecimal(0);
        for(int i = 0; i < arrayData.length; i++){
            switch(i){
                case 0:
                    lastName = arrayData[i];
                    System.out.println(lastName);
                    if(lastName == null){
                        throw new NoDateAvailableException("Нет данных в фамилии.");
                    }
                    break;
                case 1:
                    firstName = arrayData[i];
                    System.out.println(firstName);
                    if(firstName == null){
                        throw new NoDateAvailableException("Нет данных в имени.");
                    }
                    break;
                case 2:
                    patronymic = arrayData[i];
                    System.out.println(patronymic);
                    if(patronymic == null){
                        throw new NoDateAvailableException("Нет данных в отчестве.");
                    }
                    break;
                case 3:
                    date = arrayData[i];
                    System.out.println(date);
                    if(date == null){
                        throw new NoDateAvailableException("Нет данных в  дате рождения.");
                    }
                    if(date.length() != 10){
                        throw new InvalidDataFormatException("Не правильный формат данных в дате рождения.");
                    }
                    break;
                case 4:
                    tel = arrayData[i];
                    if(tel == null){
                        throw new NoDateAvailableException("Нет данных в номере телефона.");
                    }
                    try{
                        if(tel instanceof String){
                            throw new InvalidDataFormatException("Не правильный формат данных для номера телефона.");
                        }
                    }
                    catch(InvalidDataFormatException e){
                        telephone = new BigDecimal(tel);
                        System.out.println(telephone);
                    }
                    break;
                case 5:
                    gender =arrayData[i];
                    System.out.println(gender);
                    if(gender.equals("m") || gender.equals("f")){}
                    else{
                        throw new InvalidDataFormatException("Не правильный формат данных пола человека.");
                    }                    
                    break;
            
            }
        }
        
        try(FileWriter file = new FileWriter(lastName + ".txt", true)){
            file.write("<" + lastName + ">");
            file.write("<" + firstName + ">");
            file.write("<" + patronymic + ">");
            file.write("<" + date + ">");
            file.write("<" + telephone + ">");
            file.write("<" + gender + ">\n");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    

    static class CountDataException extends RuntimeException{
        public CountDataException(String message){
            super(message);
        }
    }

    static class InvalidDataFormatException extends RuntimeException{
        public InvalidDataFormatException(String message){
            super(message);
        }
    }

    static class NoDateAvailableException extends RuntimeException{
        public NoDateAvailableException(String message){
            super(message);
        }
    }
}
