package control;
import java.util.List;

public interface IManager<T> {
    String add(T entity);
    String update(String id, T newEntity);
    String delete(String id);
    List<T> getAll();
}