package com.softraysolutions.news.service;

import com.softraysolutions.news.NewsApplication;
import com.softraysolutions.news.model.User;
import com.softraysolutions.news.model.enumeration.Enumerations;
import com.softraysolutions.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ServiceInterface<User>, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public User getById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(NewsApplication.bCryptPasswordEncoder().encode(user.getPassword()));
        user.setUserType(Enumerations.UserType.Admin);
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> optional = repository.findById(user.getId());
        if (optional.isPresent()) {
            User existing = optional.get();
            existing.setPassword(NewsApplication.bCryptPasswordEncoder().encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return user;
    }
}
