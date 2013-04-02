/**
 * 
 */
package aop;

/**
 * @author zoza
 * 
 */
public class TestObject {
    public TestObject(String name, long id) {
        super();
        this.name = name;
        this.id = id;
    }

    private String name;
    private long id;

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TestObject [name=" + name + ", id=" + id + "]";
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
