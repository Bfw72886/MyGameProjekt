# Projekt MyGame

We worked on this task alone as a kind of final project in android class.  
The project is a good summarization about what we learned in android class.

## Requirements

### General requirements

- Title and buttontext must have the same color
- Buttons must have an icon on the left and text on the right
- Texts must only be defined in the strings.xml
- Colors must only be defined in the colors.xml or be predefined
- Classes must be documented with javadoc comments

### Activities & layouts

#### RegisterActivity / RegisterView:
- username, password and password confirmation to register
- show Toasts if something went wrong
- create MyGameUser object when successfully logged in

#### LoginActivity / LoginView:
- username and password to log in
- show RegisterView if not registered yet
- show Toasts if something went wrong
- if logged in successfully, show main menu

#### MainActivity / MainView:
- buttons: "Start Game“, "Highscores“, "Settings“ and "About My Game“
- buttons should switch to corresponding activity
- buttons must be centered horizontally
- title "My Game" with font size 40

#### StartGameActivity / StartGameView:
- centered text "Coming soon..."

#### HighscoresActivtiy / HighscoresView:
- 5 best players should be listed
- playername, level and points should be shown
- current user should be listed on top
- other players should be generated with an additional method
- button to switch to "GameStartActivity"

#### SettingsActivity / SettingsView:
- current username should be centered horizontally

#### AboutGameActivity / AboutGameView:
- current username should be centered horizontally at the bottom

## Bonus tasks

#### From teacher:
- enable user to change username and save it (in settings)
- have the corresponding inputfield be filled with the current username
- show Toast, if inputfield is empty

#### From myself: 
- use themes to enable user to switch between light- and darkmode
- save this mode and start app with saved theme
- add icon that shows up in home menu and in Toasts