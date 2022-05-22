package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.dao.FollowRequestRepository;
import cz.nss.onegram.user.dao.MessageRepository;
import cz.nss.onegram.user.model.FollowRequest;
import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class SystemInitializerImpl implements SystemInitializer {

    private final UserServiceImpl userService;
    private final MessageRepository messageRepository;
    private final FollowRequestRepository followRequestRepository;
    private final Environment environment;

    private final String EMAIL_POSTFIX = "@fel.cvut.cz";

    private User cuphuon3;
    private User belkapre;
    private User bureson2;
    private User pivonja1;
    private User naplava;
    private User koci;
    private User sebekji1;
    private User nagyoing;

    @Override
    @PostConstruct
    public void initSystem() {
        if (Arrays.asList(environment.getActiveProfiles()).contains("testprofile")) {
            log.info("Testprofile");
            return;
        }
        generateUser();
        generateFollowing();
        generateMessage();
    }

    public void generateUser() {
        log.info("Generating users.");

        cuphuon3 = User.builder()
                .email("cuphuon3" + EMAIL_POSTFIX)
                .username("cuphuon3")
                .created(LocalDateTime.now())
                .bio("Jsem nejhezci")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(cuphuon3);

        belkapre = User.builder()
                .email("belkapre" + EMAIL_POSTFIX)
                .username("belkapre")
                .created(LocalDateTime.now())
                .bio("Dobre jak cyp hej")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(belkapre);


        bureson2 = User.builder()
                .email("bureson2" + EMAIL_POSTFIX)
                .username("bureson2")
                .created(LocalDateTime.now())
                .bio("Sparta❤")
                .isPublic(true)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(bureson2);


        pivonja1 = User.builder()
                .email("pivonja1" + EMAIL_POSTFIX)
                .username("pivonja1")
                .created(LocalDateTime.now())
                .bio("192.168.1.0 Is the only place")
                .isPublic(true)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(pivonja1);


        naplava = User.builder()
                .email("naplava" + EMAIL_POSTFIX)
                .username("naplava")
                .created(LocalDateTime.now())
                .isPublic(true)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(naplava);

        koci = User.builder()
                .email("koci" + EMAIL_POSTFIX)
                .username("koci")
                .created(LocalDateTime.now())
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(koci);

        sebekji1 = User.builder()
                .email("sebekji1" + EMAIL_POSTFIX)
                .username("sebekji1")
                .created(LocalDateTime.now())
                .bio("Nemuzu rict neeee, ale")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(sebekji1);

        nagyoing = User.builder()
                .email("nagyoing" + EMAIL_POSTFIX)
                .username("nagyoing")
                .created(LocalDateTime.now())
                .bio("Foo foo foo")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(nagyoing);


        log.info("users were generated.");
    }

    public void generateFollowing() {
        log.info("Generating following.");

        //Naplava sleduje Kociho
        koci.addFollower(naplava);
        naplava.addFollowing(koci);

        //Koci sleduje Naplavu
        naplava.addFollower(koci);
        koci.addFollowing(naplava);

        //Koci sleduje Sebka
        sebekji1.addFollower(koci);
        koci.addFollowing(sebekji1);

        //Koci sleduje Ondreje
        bureson2.addFollower(koci);
        koci.addFollowing(bureson2);

        //Sebek sleduje Nagyovou
        nagyoing.addFollower(sebekji1);
        sebekji1.addFollowing(nagyoing);

        //Nagyova sleduje Cua
        cuphuon3.addFollower(nagyoing);
        nagyoing.addFollowing(cuphuon3);

        //<editor-fold desc="Cu nekoho sleduje">
        //Cu sleduje Ondreje
        bureson2.addFollower(cuphuon3);
        cuphuon3.addFollowing(bureson2);

        //Cu sleduje Premka
        belkapre.addFollower(cuphuon3);
        cuphuon3.addFollowing(belkapre);

        //Cu sleduje Honzika
        pivonja1.addFollower(cuphuon3);
        cuphuon3.addFollowing(pivonja1);

        //Cu sleduje Naplavu
        naplava.addFollower(cuphuon3);
        cuphuon3.addFollowing(naplava);

        //Cu sleduje Kociho
        koci.addFollower(cuphuon3);
        cuphuon3.addFollowing(koci);

        //Cu sleduje Nagyovou
        nagyoing.addFollower(cuphuon3);
        cuphuon3.addFollowing(nagyoing);

        //Cu sleduje Sebka
        sebekji1.addFollower(cuphuon3);
        cuphuon3.addFollowing(sebekji1);
        //</editor-fold>

        //<editor-fold desc="Premek nekoho sleduje">
        //Premek sleduje Cua
        cuphuon3.addFollower(belkapre);
        belkapre.addFollowing(cuphuon3);

        //Premek sleduje Ondreje
        bureson2.addFollower(belkapre);
        belkapre.addFollowing(bureson2);

        //Premek sleduje Honzika
        pivonja1.addFollower(belkapre);
        belkapre.addFollowing(pivonja1);

        //Premek sleduje Nagyovou
        nagyoing.addFollower(belkapre);
        belkapre.addFollowing(nagyoing);
        //</editor-fold>

        //<editor-fold desc="Ondrej nekoho sleduje">
        //Ondrej sleduje Cua
        cuphuon3.addFollower(bureson2);
        bureson2.addFollowing(cuphuon3);

        //Ondrej sleduje Premka
        belkapre.addFollower(bureson2);
        bureson2.addFollowing(belkapre);

        //Ondrej sleduje Honzu
        pivonja1.addFollower(bureson2);
        bureson2.addFollowing(pivonja1);

        //Ondrej sleduje Naplavu
        naplava.addFollower(bureson2);
        bureson2.addFollowing(naplava);
        //</editor-fold>

        //<editor-fold desc="Honzik nekoho sleduje">
        //Honzik sleduje Cua
        cuphuon3.addFollower(pivonja1);
        pivonja1.addFollowing(cuphuon3);

        //Honzik sleduje Premka
        belkapre.addFollower(pivonja1);
        pivonja1.addFollowing(belkapre);

        //Honzik sleduje Ondreje
        bureson2.addFollower(pivonja1);
        pivonja1.addFollowing(bureson2);
        //</editor-fold>

        userService.persist(cuphuon3);
        userService.persist(belkapre);
        userService.persist(bureson2);
        userService.persist(pivonja1);
        userService.persist(naplava);
        userService.persist(koci);
        userService.persist(sebekji1);
        userService.persist(nagyoing);


        //Koci poslal zadost o sledovani Nagyove
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(koci)
                        .receiver(nagyoing)
                        .date(LocalDateTime.now())
                        .build()
        );

        //Koci poslal zadost o sledovani Premka
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(koci)
                        .receiver(belkapre)
                        .date(LocalDateTime.now())
                        .build()
        );

        //Sebek poslal zadost o sledovani Nagyove
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(sebekji1)
                        .receiver(nagyoing)
                        .date(LocalDateTime.now())
                        .build()
        );

        //Nagyova poslala zadost o sledovani Sebkovi
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(nagyoing)
                        .receiver(sebekji1)
                        .date(LocalDateTime.now())
                        .build()
        );

        //Nagyova poslala zadost o sledovani Premkovi
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(nagyoing)
                        .receiver(belkapre)
                        .date(LocalDateTime.now())
                        .build()
        );

        //Naplava poslala zadost o sledovani Cua
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(naplava)
                        .receiver(cuphuon3)
                        .date(LocalDateTime.now())
                        .build()
        );


        log.info("following were generated.");
    }

    public void generateMessage() {
        log.info("Generating messages.");

        //<editor-fold desc="Konverzace ja a premda">
        messageRepository.save(
                Message.builder()
                        .message("Ahoj jak se mas")
                        .date(LocalDateTime.now())
                        .sender(cuphuon3)
                        .receiver(belkapre)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Mam se dobre")
                        .date(LocalDateTime.now())
                        .sender(belkapre)
                        .receiver(cuphuon3)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("To to rad slysim")
                        .date(LocalDateTime.now())
                        .sender(cuphuon3)
                        .receiver(belkapre)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Co ty?")
                        .date(LocalDateTime.now())
                        .sender(belkapre)
                        .receiver(cuphuon3)
                        .hasRead(false)
                        .isDeleted(false)
                        .build()
        );
        //</editor-fold>

        //<editor-fold desc="Konverzace Koci a Naplava">
        messageRepository.save(
                Message.builder()
                        .message("Dame jidlo?")
                        .date(LocalDateTime.now())
                        .sender(koci)
                        .receiver(naplava)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Nemam moc hlad")
                        .date(LocalDateTime.now())
                        .sender(naplava)
                        .receiver(koci)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Nebud kyblik, dej si pytlik")
                        .date(LocalDateTime.now())
                        .sender(koci)
                        .receiver(naplava)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Ti dam pytlik ty @$%*#")
                        .date(LocalDateTime.now())
                        .sender(naplava)
                        .receiver(koci)
                        .hasRead(true)
                        .isDeleted(true)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Ale jo")
                        .date(LocalDateTime.now())
                        .sender(naplava)
                        .receiver(koci)
                        .hasRead(true)
                        .isDeleted(true)
                        .build()
        );
        //</editor-fold>

        log.info("messages were generated.");

    }
}
