package com.mms.CamsEcommerceReviews.User;

import com.mms.CamsEcommerceReviews.Product.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    List<User> findAll() {
        return userRepository.findAll();
    }

    User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with ID=" + id + " doesn't exist");
        }
        return userOptional.get();
    }

    User save(User user) {
        return userRepository.save(user);
    }

    void delete(Long id) {
        userRepository.deleteById(id);
    }
}
