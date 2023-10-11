package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.CommentRecord;
import com.kenzie.appserver.repositories.model.UserRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface UserRepository extends CrudRepository<UserRecord, String> {

    //possible idea for basic authentication, would require hashing and a secondary index irl
    //List<UserRecord> findByUsernameAndPassword(String username, String password);
    //-adam

}
