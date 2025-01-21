import pandas as pd
import psycopg2
import string
import sys
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from youtube_transcript_api import YouTubeTranscriptApi


# Ensure NLTK resources are available
# nltk.download('punkt')
# nltk.download('stopwords')

def get_transcript(video_id):
    """Fetch the transcript of a YouTube video."""
    try:
        # Fetch the transcript
        transcript = YouTubeTranscriptApi.get_transcript(video_id)
        # Combine all transcript text into one string
        transcript_text = " ".join([entry['text'] for entry in transcript])
        return transcript_text
    except Exception as e:
        print(f"Error fetching transcript for video {video_id}: {e}")
        return None


def extract_meaningful_words(text):
    """Extract meaningful words by removing stopwords and punctuation."""
    # Tokenize the text into words
    words = word_tokenize(text.lower())
    # Remove stopwords and punctuation
    stop_words = set(stopwords.words('english'))
    meaningful_words = [
        word for word in words
        if word not in stop_words and word not in string.punctuation
    ]
    return meaningful_words


def get_word_frequencies(words):
    """Calculate the frequency of impactful words using pandas."""
    # Create a DataFrame from the list of words
    df = pd.DataFrame(words, columns=["word"])
    # Calculate word frequencies
    word_counts = df["word"].value_counts().reset_index()
    word_counts.columns = ["word", "count"]
    return word_counts


def save_to_postgresql(video_id, word_frequencies, db_config):
    """Save word frequencies to a PostgreSQL table."""
    try:
        # Connect to PostgreSQL database
        conn = psycopg2.connect(**db_config)
        cursor = conn.cursor()

        # Insert word frequencies into the database
        insert_query = """INSERT INTO word_frequencies (video_id, word, count) VALUES (%s, %s, %s)"""
        for _, row in word_frequencies.iterrows():
            cursor.execute(insert_query, (video_id, row["word"], row["count"]))

        insert_video_id_query = """INSERT INTO onboarded_videos (video_id) VALUES (%s)"""
        cursor.execute(insert_video_id_query, (video_id,))
        # Commit the transaction
        conn.commit()
        print("Word frequencies saved to PostgreSQL database.")


    except Exception as e:
        print(f"Error saving to PostgreSQL: {e}")
    finally:
        if conn:
            cursor.close()
            conn.close()


def main(video_id,db_name):
    """Main function to process the video transcript."""
    # Database configuration
    db_config = {
        "dbname": db_name,
        "user": "admin",
        "password": "admin",
        "host": "localhost",  # or your DB server address
        "port": 5432  # default PostgreSQL port
    }

    # Get the transcript
    transcript_text = get_transcript(video_id)
    if transcript_text:
        # Extract meaningful words
        meaningful_words = extract_meaningful_words(transcript_text)
        # Get word frequencies
        word_frequencies = get_word_frequencies(meaningful_words)
        # Save to PostgreSQL
        save_to_postgresql(video_id, word_frequencies, db_config)


main("6m3XjNtAgkA","aggregation_service_database")
# if __name__ == "__main__":
#     if len(sys.argv) != 2:
#         sys.exit(1)

#     video_id = sys.argv[1]
#     db_name = sys.argv[2]
#     main(video_id,db_name)
