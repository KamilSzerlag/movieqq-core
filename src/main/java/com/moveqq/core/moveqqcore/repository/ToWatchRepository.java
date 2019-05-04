package com.moveqq.core.moveqqcore.repository;

import com.moveqq.core.moveqqcore.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface ToWatchRepository extends CrudRepository<UserEntity, Long>{
}
