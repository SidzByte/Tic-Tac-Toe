# Tic-Tac-Toe Android App

A modern, visually appealing Tic-Tac-Toe game built with **Java** and **Material Design 3**. This project demonstrates clean UI principles, responsive layouts, and interactive animations for a polished user experience.

## 🚀 Features

*   **Modern UI**: Built using Material 3 components for a sleek, "experienced developer" look.
*   **Responsive Design**: Uses `ConstraintLayout` and `GridLayout` to ensure the game board looks great on any screen size.
*   **Interactive Animations**:
    *   **Tap Feedback**: Subtle scale animations when a player marks a cell.
    *   **Winner Highlight**: Smooth color interpolation and "pop" animations for the winning line.
*   **Clear Game State**: Real-time status updates showing whose turn it is or who won/drew the game.
*   **Distinct Visuals**: Different color schemes for Player X (Red) and Player O (Blue) for better clarity.

## 🛠️ Built With

*   **Language**: Java
*   **UI Framework**: Android XML with Material Design 3
*   **Architecture**: View-based with logical separation of game states.
*   **Minimum SDK**: API 24 (Android 7.0)
*   **Target SDK**: API 36

<!-- ## 📸 Screenshots -->

<!-- *(Add your screenshots here to make it look even more professional!)*
-->

## 🎮 How to Play

1.  The game starts with **Player X**.
2.  Players take turns clicking on any empty cell in the 3x3 grid.
3.  The first player to get 3 of their marks in a row (up, down, across, or diagonally) is the winner.
4.  If all 9 cells are filled and no player has 3 marks in a row, the game is a draw.
5.  Click the **"Restart Game"** button to play again at any time.

## 📂 Project Structure

*   `MainActivity.java`: Contains the core game logic, winner detection, and animation sequences.
*   `activity_main.xml`: The layout definition using `ConstraintLayout` and `MaterialButton`.
*   `colors.xml`: Defines a custom Material color palette for the app.

---
Developed as a showcase of modern Android development practices.