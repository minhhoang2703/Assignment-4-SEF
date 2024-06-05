import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest{
    @Test
    public void testAddPost() {
        // Create a new Post object
        Post post = new Post();

        // Set the post details
        post.setPostID(1);
        post.setPostTitle("This is a valid post title hehehehehe");
        post.setPostBody("Life is a journey filled with unexpected twists and turns. Each day brings its own set of challenges and rewards. We learn, grow, and evolve with every experience. Our strength lies in our ability to adapt, our resilience, and our unyielding spirit to strive for greatness.");
        post.setPostTags(new String[]{"tag1", "tag2", "tag3"});
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
        post.setPostTitle("This is a valid post title hehehehehe");
        post.setPostBody("Life is a journey filled with unexpected twists and turns. Each day brings its own set of challenges and rewards. We learn, grow, and evolve with every experience. Our strength lies in our ability to adapt, our resilience, and our unyielding spirit to strive for greatness.");
        post.setPostTags(new String[]{"tag1", "tag2", "tag3"});
        post.getPostTypes()[post.getPostID()] = "Easy";
        post.getPostEmergency()[post.getPostID()] = "Ordinary";

        // Add comments and assert the result
        String[] comments = {"This is a valid comment.", "Yet another valid comment."};
        for (String comment : comments) {
            boolean addCommentResult = post.addComment(comment);
            assertTrue(addCommentResult, "Expected addComment to return true, but it returned false");
        }
    }
}

