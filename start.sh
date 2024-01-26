GRADLE_ERROR="Please install Gradle!"

echo "Clearing old build..."
if gradle clean; then
  echo "Build cleared!"
else
  echo $GRADLE_ERROR
  exit
fi

echo "Building and starting..."
if gradle bootRun; then
  echo "Working 0_0"
else
  echo "Bye! Bye!"
  exit
fi
