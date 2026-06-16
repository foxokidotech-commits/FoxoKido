# FoxoCraft Client - Minecraft 1.8.9 PvP Client

A professional and feature-rich PvP client for Minecraft 1.8.9 with advanced modules, customizable HUD, and account management.

## Features

### Core Systems
- **Module System**: Easily extensible module framework for adding new features
- **ClickGUI**: Draggable panels with organized module categories
- **HUD System**: Customizable HUD elements with drag-and-drop editor mode
- **Account Manager**: Manage multiple Minecraft accounts
- **Profile Manager**: Create and manage game profiles with custom settings
- **Settings System**: Persistent configuration storage

### Modules

#### Movement
- **Zoom**: Zoom in and out with customizable levels
- **ToggleSprint**: Enable/disable automatic sprinting

#### Render
- **Fullbright**: Maximum brightness without torches
- **FPS Counter**: Display current frames per second
- **CPS Counter**: Show left/right clicks per second
- **Keystrokes**: Display WASD and Space key presses
- **Armor Status**: Monitor armor durability in real-time
- **Coordinates**: Display current XYZ position
- **Waypoints**: Create and track custom waypoints

### HUD Elements
- FPS Counter
- CPS Counter
- Keystrokes Display
- Coordinates Display
- Armor Status
- Waypoints List
- FoxoCraft Watermark
- Drag-and-drop editing mode

### Additional Features
- **Cosmetics Manager**: Support for custom capes
- **Screenshot Manager**: Organized screenshot storage and management
- **Custom Main Menu**: FoxoCraft themed main menu
- **Utility Functions**: Chat, player, and render utilities

## Installation

1. Install Minecraft Forge 1.8.9
2. Download the latest FoxoCraft release
3. Place the JAR in your `.minecraft/mods` folder
4. Launch Minecraft with the Forge profile

## Building from Source

```bash
# Clone the repository
git clone https://github.com/foxokidotech-commits/FoxoKido.git
cd FoxoKido

# Build with Gradle
./gradlew build

# JAR will be in build/libs/
```

## Usage

### Opening ClickGUI
Press **C** to open the ClickGUI (configurable in settings)

### HUD Edit Mode
Press **H** to enter HUD edit mode where you can drag and drop HUD elements

### Account Management
- Access via main menu → FoxoCraft Settings → Accounts
- Add new accounts and manage multiple profiles

### Profile Management
- Create custom profiles with different module configurations
- Switch profiles on the fly

## Controls

- **C Key**: Toggle ClickGUI
- **H Key**: Toggle HUD Edit Mode
- **ESC**: Close GUI
- **Mouse Wheel**: Zoom (in Zoom module)
- **V Key**: Toggle Sprint
- **F Key**: Toggle Fullbright

## Configuration

All settings are stored in `.foxocraft/config/` directory:
- `settings.json` - Client settings
- `accounts/` - Account data
- `profiles/` - Game profiles
- `screenshots/` - Screenshot storage
- `waypoints/` - Custom waypoints

## Development

### Project Structure
```
src/main/java/pl/foxocraft/
├── module/          # Module system
├── hud/            # HUD system and elements
├── gui/            # GUI components and screens
├── account/        # Account management
├── profile/        # Profile management
├── settings/       # Settings system
├── cosmetics/      # Cosmetics and capes
├── screenshot/     # Screenshot management
├── event/          # Event system
├── utils/          # Utility classes
└── FoxoCraft.java  # Main entry point
```

## License

All rights reserved. FoxoCraft Client © 2024

## Contributing

Contributions are welcome! Please feel free to submit pull requests.

## Support

For issues and support, please visit: https://github.com/foxokidotech-commits/FoxoKido/issues

---

**FoxoCraft Client v1.0.0** - Made with ❤️ for the Minecraft community
