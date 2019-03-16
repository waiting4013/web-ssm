package annotation;

public class User {


    private String name;
    private String age;

    public String getName()
    {
        return name;
    }

    @test(value = "liang")
    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    @test(value = "23")
    public void setAge(String age)
    {
        this.age = age;
    }
}
