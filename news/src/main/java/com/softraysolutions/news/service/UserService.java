package com.softraysolutions.news.service;

import com.softraysolutions.news.model.User;
import com.softraysolutions.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ServiceInterface<User> {

    @Autowired
    private UserRepository repository;

    @Override
    public User getById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(user.getPassword());
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> optional = repository.findById(user.getId());
        if (optional.isPresent()) {
            User existing = optional.get();
            existing.setPassword(user.getPassword());
            return repository.save(existing);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
