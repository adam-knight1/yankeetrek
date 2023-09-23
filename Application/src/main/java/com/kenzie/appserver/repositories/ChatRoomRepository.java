package com.kenzie.appserver.repositories;
import com.kenzie.appserver.repositories.model.ChatRoomRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
@EnableScan
public interface ChatRoomRepository extends CrudRepository<ChatRoomRecord, String> {
    //added by adam
}
