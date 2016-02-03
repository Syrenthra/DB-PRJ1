package Program2;

public class EmployeeSale
{
    String id;
    double base;

    public EmployeeSale(String s, double sale)
    {
        id=s;
        base=sale;
    }
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public double getBase()
    {
        return base;
    }

    public void setBase(double base)
    {
        this.base = base;
    }


}
