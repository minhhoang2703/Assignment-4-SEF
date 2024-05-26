import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Post {
    private int postId;
    private String postTitle;
    private String postBody;
    private ArrayList<String> postTags;
    private String postDifficulty;
    private String postEmergency;
    private ArrayList<String> postComments;

    public Post(int postId, String postTitle, String postBody, ArrayList<String> postTags, String postDifficulty, String postEmergency) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postDifficulty = postDifficulty;
        this.postEmergency = postEmergency;
        this.postComments = new ArrayList<>();
    }

    public int getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public ArrayList<String> getPostTags() {
        return postTags;
    }

    public String getPostDifficulty() {
        return postDifficulty;
    }

    public String getPostEmergency() {
        return postEmergency;
    }

    public ArrayList<String> getPostComments() {
        return postComments;
    }

    public boolean isValidPost() {
        // Check condition 1: title length
        if (postTitle.length() < 10 || postTitle.length() > 250 || !Character.isLetter(postTitle.charAt(0))) {
            return false;
        }

        // Check condition 2: body length
        if (postBody.length() < 250) {
            return false;
        }

        // Check condition 3: tag count and format
        if (postTags.size() < 2 || postTags.size() > 5) {
            return false;
        }
        for (String tag : postTags) {
            if (tag.length() < 2 || tag.length() > 10 || Character.isUpperCase(tag.charAt(0))) {
                return false;
            }
        }

        // Check condition 4: difficulty
        if (postDifficulty.equals("Very Difficult") || postDifficulty.equals("Difficult")) {
            if (postBody.length() < 300) {
                return false;
            }
        } else if (postDifficulty.equals("Easy") && postTags.size() > 3) {
            return false;
        }

        // Check condition 5: emergency status
        if ((postDifficulty.equals("Easy") && (postEmergency.equals("Immediately Needed") || postEmergency.equals("Highly Needed"))) ||
                (postDifficulty.equals("Very Difficult") || postDifficulty.equals("Difficult")) && !postEmergency.equals("Ordinary")) {
            return false;
        }

        return true;
    }
    public boolean addComment(String comment) {
        // Check condition 1: comment text length
        String[] words = comment.split("\\s+");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }

        // Check condition 2: comment count for easy and ordinary posts
        if ((postDifficulty.equals("Easy") || postEmergency.equals("Ordinary")) && postComments.size() >= 3) {
            return false;
        }

        // Add comment to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("comment.txt", true))) {
            writer.write(String.valueOf(postId));
            writer.newLine();
            writer.write(comment);
            writer.newLine();
            writer.write(String.valueOf(postComments.size()));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Add comment to post comments list
        postComments.add(comment);

        return true;
    }

    
}
    public static void main(String[] args) {
        // Create a new Post object
        Post post = new Post(1, "Example Post", "This is an example post body.", new ArrayList<String>(){{add("tag1"); add("tag2");}}, "Difficult", "Immediately Needed");

        // Add comments to the post
        post.addComment("This is the first comment.");
        post.addComment("This is the second comment.");

        // Print the post information
        System.out.println("Post ID: " + post.getPostId());
        System.out.println("Post Title: " + post.getPostTitle());
        System.out.println("Post Body: " + post.getPostBody());
        System.out.println("Post Tags: " + post.getPostTags());
        System.out.println("Post Difficulty: " + post.getPostDifficulty());
        System.out.println("Post Emergency: " + post.getPostEmergency());
        System.out.println("Post Comments: " + post.getPostComments());
    }
