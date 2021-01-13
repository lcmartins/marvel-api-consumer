INSTRUCTIONS

after that as soon as you have your $JAVA_HOME set to java11 path
and have followed the steps described in "vars.sh"
issue the command: gradle bootRun -Dorg.gradle.java.home=$JAVA_HOME
after that you may have the following urls to play with:
    # yourhost:8080/characters
    # yourhost:8080/characters/image
    # example:  
        # localhost:8080/characters
        # localhost:8080/characters/image?url=http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16&size=INCREDIBLE