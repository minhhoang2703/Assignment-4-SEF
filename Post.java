import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};
    private ArrayList<String> postComments = new ArrayList<>();

    public boolean addPost() {
        // Condition 1: Check post title length and first five characters
        if (postTitle.length() < 10 || postTitle.length() > 250 || !Pattern.matches("[a-zA-Z ]{5}.*", postTitle)) {
            return false;
        }

        // Condition 2: Check post body length
        if (postBody.length() < 250) {
            return false;
        }

        // Condition 3: Check post tags
        if (postTags.length < 2 || postTags.length > 5) {
            return false;
        }
        for (String tag : postTags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                return false;
            }
        }

        // Condition 4: Check post types and body length
        if (("Easy".equals(postTypes[postID]) && postTags.length > 3) || (("Very Difficult".equals(postTypes[postID]) || "Difficult".equals(postTypes[postID])) && postBody.length() < 300)) {
            return false;
        }

        // Condition 5: Check post emergency
        if (("Easy".equals(postTypes[postID]) && ("Immediately Needed".equals(postEmergency[postID]) || "Highly Needed".equals(postEmergency[postID]))) || (("Very Difficult".equals(postTypes[postID]) || "Difficult".equals(postTypes[postID])) && "Ordinary".equals(postEmergency[postID]))) {
            return false;
        }

        // If all conditions are met, write to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("post.txt", true));
            writer.write("Post ID: " + postID + "\n");
            writer.write("Post Title: " + postTitle + "\n");
            writer.write("Post Body: " + postBody + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addComment(String comment){
        String [] words = comment.split("\\s+");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(words[0].charAt(0))){
            return false;
        }
        if (postComments.size() >= 5 || (("Easy".equals(postTypes[postID]) || "Ordinary".equals(postEmergency[postID])) && postComments.size() >= 3)) {
            return false;
        }
        postComments.add(comment);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("comment.txt", true));
            writer.write("Post ID: " + postID + ", Comment: " + comment + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        // Create a new Post object
        Post post = new Post();
    
        // Set the post details
        post.postID = 1;
        post.postTitle = "This is a valid post title";
        post.postBody = "Life is like a journey on a train with its stations, with changes of routes and with accidents! At birth, we boarded the train and met our parents, and we believe they will always travel on our side. However, at some station our parents will step down from the train, leaving us on this journey alone.";
        post.postTags = new String[]{"tag1", "tag2", "tag3"};
        post.postTypes[post.postID] = "Difficult";
        post.postEmergency[post.postID] = "Immediately Needed";
    
        // Call the addPost method and print the result
        boolean result = post.addPost();
        System.out.println("Result of addPost: " + result);

            // Add comments and print the result
    String[] comments = {"This is a valid comment.", "Another valid comment.", "Yet another valid comment."};
    for (String comment : comments) {
        boolean addCommentResult = post.addComment(comment);
        System.out.println("Result of addComment: " + addCommentResult);
    }
    }
    
}


