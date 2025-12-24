# 🚢 Battleship Game (Java)

This is a robust, two-player terminal-based Battleship game implemented in Java. The project features strict coordinate validation, fleet management, and an efficient damage tracking system.

The project is structured into three main files: `Start.java` (game controller), `Player.java` (player data), and `Boat.java` (ship logic).

## 🚀 How to Run

1. **Clone the repository:**
    ```bash
    git clone [https://github.com/Trib20dev/battleship-task](https://github.com/Trib20dev/battleship-task)
    ```

2. **Compile the files:** Navigate to the project directory and compile the Java source files using your preferred JDK.
    ```bash
    javac main_container/*.java
    ```

3. **Execute the main class:**
    ```bash
    java main_container.Start
    ```

## 💻 Code Structure

| File | Purpose | Key Methods |
| :--- | :--- | :--- |
| **`Start.java`** | Handles the main game loop, turn switching, and input validation. | `main()`, `boatMayExist()`, `emptyAround()` |
| **`Player.java`** | Manages the fleet, shot history (hits/misses), and board printing. | `setBoatPosition()`, `removeLastBoat()`, `printMyBoats()` |
| **`Boat.java`** | Tracks individual ship coordinates and calculates damage/sunk status. | `setCoordinates()`, `setStateHitAt()`, `isSunk()` |

## 🎮 Gameplay Features

* **Advanced Validation**: The game prevents diagonal placement and enforces a "buffer zone" (ships cannot touch each other).
* **Fleet Management**: During setup, use the command `REMOVE BOAT` to undo your last placement and correct errors.
* **In-Game Commands**: During your turn, you can type special commands without losing your shot:
    * `SEE MY BOATS`: View your own fleet and damage taken.
    * `SEE MY SHOTS`: View a map of your previous attacks on the enemy.

## ⚙️ Game Logic

The board is a 10x10 grid (A-J, 0-9). The game uses `HashSet` for O(1) coordinate lookups and parallel lists within the `Boat` class to synchronize physical locations with their current state (Intact/Hit).
