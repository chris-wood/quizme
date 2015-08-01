#!/bin/bash
osascript <<END
    tell application "iTerm"
        set myterm to (make new terminal)
        tell myterm
            launch session "Default Session"
            tell the last session
            	write text "cd /Users/caw/Projects/quizme/src/"
                write text "java -cp simplenlg-v4.4.2.jar:. Translator ../apikey"
            end tell
        end tell
    end tell
END
## java -cp simplenlg-v4.4.2.jar:. Translator /Users/caw/Projects/quizme/apikey