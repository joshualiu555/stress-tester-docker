To keep things simple, you are required to have three files in your root directory called:
1. Generator.java
2. Solution.java
3. BruteForce.java

There is currently a Docker image under `joshualiu555/stress-tester-docker`. 

To use the docker image, run the command `docker run --rm -v $(pwd):/app -w /app joshualiu555/stress-tester-docker`.

If you want to simplify everything, a bash file is included in this repo. Simply add that to your project's root directory. 
Then run these commands:
1. chmod +x stress-tester-docker.sh
2. ./stress-tester-docker.sh
