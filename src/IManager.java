import java.util.List;

public interface IManager<T> {

    String add(T object);

    String update(String id, T newObject);

    String delete(String id);

    void showAll();

}