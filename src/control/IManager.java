package control;
import java.util.List;

public interface IManager<T> {
    String add(T entity);
    String update(String code, T newEntity);
    String delete(String code);

    void showAll();

    List<T> getAll();
}