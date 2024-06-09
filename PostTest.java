import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest{
    @Test
    public void testAddPost() {
        // Create a new Post object
        Post post = new Post();

        // Set the post details
        post.setPostID(1);
        post.setPostTitle("The story of a goat");
        post.setPostBody("Goats, known for their agility and curiosity, are versatile animals. ");
        post.setPostTags(new String[]{"Animal","Farm","Barn"});
        post.getPostTypes()[post.getPostID()] = "Easy";
        post.getPostEmergency()[post.getPostID()] = "Ordinary";

        // Call the addPost method and assert the result
        boolean result = post.addPost();
            assertTrue(result, "Expected addPost to return true, but it returned false");
    }

    @Test
    public void testAddComment() {
        // Create a new Post object
        Post post = new Post();

        // Set the post details
        post.setPostID(1);
        post.setPostTitle("The story of a goat");
        post.setPostBody("Goats, known for their agility and curiosity, are versatile animals. They provide milk, meat, and fiber, and can adapt to nearly any climate. Their playful antics and gentle nature make them beloved pets and farm animals. They’re truly remarkable creatures.");
        post.setPostTags(new String[]{"Animal","Farm","Barn"});
        post.getPostTypes()[post.getPostID()] = "Easy";
        post.getPostEmergency()[post.getPostID()] = "Ordinary";

        // Add comments and assert the result
        String[] comments = {"Why we ask about a goat."};
        for (String comment : comments) {
            boolean addCommentResult = post.addComment(comment);
            assertTrue(addCommentResult, "Expected addComment to return true, but it returned false");
        }
    }
}

