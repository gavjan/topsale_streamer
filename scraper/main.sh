# Prpare for cron
export PATH="/home/cgev/.local/bin:$PATH"

# Clean previous vidoes
rm -f temp_video.mp4 output_video.mp4
rm -rf .videos .stories stories vids
mkdir -p vids

# Download Stories and Videos
instagram-story
python3 download_videos.py

# Organize videos
count=1
mp4_files=$(find . -type f -name "*.mp4")
IFS=$'\n' # Set Internal Field Separator to newline to handle filenames with spaces
for mp4_file in $mp4_files; do
    new_name="vids/$count.mp4"
    mv "$mp4_file" "$new_name"
    count=$((count + 1))
done
unset IFS # Reset IFS to its default value

# Remove unnecessary metadata left behind
rm -rf .stories
rm -rf stories
rm -rf .videos

# Merge videos
ffmpeg -y -f concat -safe 0 -i <(for f in $(find vids -type f -name "*.mp4"); do echo "file '$PWD/$f'"; done) -c:v copy -an temp_video.mp4

# Rotate video
ffmpeg -y -i temp_video.mp4 -metadata:s:v rotate="90" -c:v libx264 -vf "transpose=2" -c:a copy output_video.mp4


# Copy video to server and delete local copy
scp output_video.mp4 mim:~/public_html/.topsale/vid.mp4

