import instaloader
import os
import requests

USERNAME = "topsale.am"
COUNT = 10
IGNORE_URL = "https://students.mimuw.edu.pl/~gc401929/.topsale/streamer/ignore.txt"


# Load ignore list from server, return empty if server is unavailable
def get_ignore_list():
    response = requests.get(IGNORE_URL)
    ret = ""
    if response.status_code == 200:
        ret = response.text
    return ret


def assert_folder(name):
    if not os.path.exists(name):
        os.makedirs(name)


def download_latest_videos(number=COUNT):
    # Prepare .videos directory
    assert_folder(".videos")
    os.chdir(".videos")

    # Load ignore list
    ignore_list = get_ignore_list()

    # Load profile
    il = instaloader.Instaloader()
    profile = instaloader.Profile.from_username(il.context, USERNAME)

    # Download Videos
    i = 0
    for post in profile.get_posts():
        if post.typename == "GraphVideo":
            if i >= number:
                break
            if post.shortcode in ignore_list:
                continue
            il.download_post(post, target=f"{i}")

            i += 1
    os.chdir("..")


def main():
    download_latest_videos()


if __name__ == "__main__":
    main()
