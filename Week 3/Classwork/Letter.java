// This is a simple example class that holds a single character

public class Letter{
    String data;

    //This returns the data stored in the Letter object
    public String getData(){
        return data;
    }

    //This changes the letter that is stored in the Letter object
    public void setData( String x){
        //We only want to store a single letter 
        //so it is an error if someone tries to store a longer string
        if(x.length() != 1){
            System.out.println("Error in Letter setData method");
            System.out.println("Attempt to store more than one character");
            System.exit(1);
        }
        else{
            data = x;
        }
    }

    //This is a basic constructor that makes an empty Letter object
    public Letter(){
        data = null;
    }
    
}
