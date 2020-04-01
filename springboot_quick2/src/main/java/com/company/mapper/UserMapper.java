package com.company.mapper;
import com.company.domain.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> queryUserList();
}
