package cz.nss.onegram.user.dao;

import cz.nss.onegram.user.model.FollowRequest;
import cz.nss.onegram.user.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRequestRepository extends JpaRepository<FollowRequest, Integer> {

    public FollowRequest findById(int id);

    @Query(value = "SELECT f FROM  FollowRequest f WHERE f.sender.id = :sender_id")
    public List<FollowRequest> getAllSentRequestFromUser(@Param("sender_id") int id);

    @Query(value = "SELECT f FROM  FollowRequest f WHERE f.receiver.id = :sender_id")
    public List<FollowRequest> getAllReceivedRequestFromUser(@Param("sender_id") int id);

    @Query(value = "SELECT f FROM  FollowRequest f WHERE f.receiver.id = :sender_id AND f.sender.id = :receiver_id")
    public FollowRequest getRequestToUser(@Param("sender_id") int senderId, @Param("receiver_id") int receiverId);
}
