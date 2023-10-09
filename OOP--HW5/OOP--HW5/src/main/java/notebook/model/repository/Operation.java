package notebook.model.repository;

import java.util.List;

/**
 *Data Access Object (DAO) layer, with DB methods
 */
public interface Operation {
    List<String> readAll();
    void saveAll(List<String> data);
}
