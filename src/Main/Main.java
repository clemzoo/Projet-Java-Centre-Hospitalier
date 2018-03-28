package Main;

public class Main {
    private String test;

    public Main(){
        test = "Bienvenu dans le projet Centre Hospitalier !";
    }

    public void Test(){
        System.out.println(test);
    }

    public static void main(String[] args) {
        Main Test = new Main();

        Test.Test();
    }
}

