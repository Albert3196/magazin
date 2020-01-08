package com.example.magazin.repos;
import com.example.magazin.domain.Message;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByNamesOrMaterialOrColorOrDescription(String names, String description, String material, String color);
    List<Message> deleteAllByNamesAndMaterialAndColorAndDescription(String names, String description, String material, String color);

    Message findById(int id);
    List<Message> deleteAllById(int id);

    @Transactional
    List<Message> deleteById(int id);

    List<Message> deleteByNames(String names);
}