package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.CommentRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface CommentRepository extends CrudRepository<CommentRecord, String> {
    List<CommentRecord> findByOwnerId(String ownerId);  //query method that spring autogenerates (hopefully)...

    List<CommentRecord> findByOwnerIdAndChatRoomId(String ownerId, String chatRoomId);

}

