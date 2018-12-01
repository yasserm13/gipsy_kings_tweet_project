
@ApplicationScoped
public class TweetRepository {

    @Inject
    private EntityManager em;

    public Tweet findById(Long id) {
        return em.find(Tweet.class, id);
    }

}
