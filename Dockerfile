FROM openjdk:17

WORKDIR /app

COPY *.java ./

RUN javac StressTesterCLI.java Generator.java Solution.java BruteForce.java

CMD ["java", "StressTesterCLI", "Generator.java", "Solution.java", "BruteForce.java"]

