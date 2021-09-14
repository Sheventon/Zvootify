package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.itis.dto.UserSignUpForm;
import ru.itis.models.Album;
import ru.itis.models.Song;
import ru.itis.models.User;
import ru.itis.repositories.AlbumsRepository;
import ru.itis.repositories.SongsRepository;
import ru.itis.repositories.UsersRepository;

import java.util.*;


@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AlbumsRepository albumsRepository;
    @Autowired
    private SongsRepository songsRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public User getById(Long id) {
        return usersRepository.getOne(id);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> optionalUser = usersRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    @Override
    public boolean existUser(String email) {
        Optional<User> optionalUser = usersRepository.findByEmail(email);
        return optionalUser.isPresent();
    }

    @Override
    public String generateSecurePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void save(UserSignUpForm userForm) {
        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .password(generateSecurePassword(userForm.getPassword()))
                .peso(20)
                .isDeleted(false)
                .userRole(User.Role.USER)
                .userState(User.State.ACTIVE)
                .build();

        usersRepository.save(user);
    }

    @Override
    public void banUser(Long id) {
        Optional<User> optionalUser = usersRepository.findById(id);
        optionalUser.ifPresent((user) -> {
            if (user.isBanned()) {
                usersRepository.updateUserState(User.State.ACTIVE, user.getId());
            } else {
                usersRepository.updateUserState(User.State.BANNED, user.getId());
            }
        });
    }

    @Override
    public void buyAlbum(User user, Long id) {
        Album album = albumsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Album not found"));
        if (album != null) {
            user.getAlbums().add(album);
            album.getUsers().add(user);
            user.setPeso(user.getPeso() - album.getCost());
            for (Song albumSong : album.getSongs()) {
                boolean flag = false;
                for (Song userSong : user.getSongs()) {
                    if (albumSong.getId().equals(userSong.getId())) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    user.getSongs().add(albumSong);
                    albumSong.getUsers().add(user);
                }
            }
            usersRepository.save(user);
        }
    }

    @Override
    public String buySong(User user, Long id) {
        Song song = songsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Song not found"));
        user.getSongs().add(song);
        song.getUsers().add(user);
        user.setPeso(user.getPeso() - song.getCost());
        usersRepository.save(user);
        return "correct";
    }

    @Override
    public String signUp(UserSignUpForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userSignUpForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("userSignUpForm", userForm);
        } else {
            save(userForm);
            return "redirect:signIn";
        }
        return "register";
    }
}
