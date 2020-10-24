package choretracker;

@SuppressWarnings("checkstyle")
public class Pair {
    private Object evnt;
    private String name = "";
    private double idNumber = 0.0;
    private double cost = 0.0;
    private Object lbl;
 
    public Pair(Object lbl, Object evnt, String name, double idNumber, double cost)
    {
        this.lbl = lbl;
        this.evnt = evnt;
        this.name = name;
        this.idNumber = idNumber;
        this.cost = cost;
    }
 
    public Pair()
    {
 
    }
 
 
    /**
     * @return Object return the evnt
     */
    public Object getEvnt() {
        return evnt;
    }

    /**
     * @param evnt the evnt to set
     */
    public void setEvnt(Object evnt) {
        this.evnt = evnt;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return double return the idNumber
     */
    public double getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(double idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return double return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }


    /**
     * @return Object return the lbl
     */
    public Object getLbl() {
        return lbl;
    }

    /**
     * @param lbl the lbl to set
     */
    public void setLbl(Object lbl) {
        this.lbl = lbl;
    }

}
