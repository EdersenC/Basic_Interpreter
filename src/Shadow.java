public class Shadow {


    public static void log(String message, Object... args){
        System.out.println(message);
        for (Object arg : args) {
            System.out.println(arg);
        }
    }




}
