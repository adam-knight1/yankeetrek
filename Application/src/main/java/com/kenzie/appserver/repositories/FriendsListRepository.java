package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.CommentRecord;
import com.kenzie.appserver.repositories.model.FriendsListRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
@EnableScan
public interface FriendsListRepository extends CrudRepository<FriendsListRecord, String> {




}
