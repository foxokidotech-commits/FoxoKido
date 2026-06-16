package pl.foxocraft.event;

import net.minecraftforge.fml.common.eventbus.EventBus;
import net.minecraftforge.fml.common.eventbus.Subscribe;
import pl.foxocraft.FoxoCraft;

public class EventManager {

    private EventBus eventBus;

    public EventManager() {
        this.eventBus = new EventBus();
    }

    public void registerListeners() {
        eventBus.register(new ClientTickListener());
        eventBus.register(new RenderListener());
        eventBus.register(new KeyInputListener());
        eventBus.register(new MouseInputListener());
    }

    public void post(Event event) {
        eventBus.post(event);
    }

    public static class Event {
        private boolean cancelled = false;

        public void cancel() {
            cancelled = true;
        }

        public boolean isCancelled() {
            return cancelled;
        }
    }

    public static class ClientTickEvent extends Event {
        public ClientTickEvent() {
        }
    }

    public static class RenderEvent extends Event {
        private float partialTicks;

        public RenderEvent(float partialTicks) {
            this.partialTicks = partialTicks;
        }

        public float getPartialTicks() {
            return partialTicks;
        }
    }

    public static class KeyInputEvent extends Event {
        private int key;

        public KeyInputEvent(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }
    }

    public static class MouseInputEvent extends Event {
        private int button;
        private boolean pressed;

        public MouseInputEvent(int button, boolean pressed) {
            this.button = button;
            this.pressed = pressed;
        }

        public int getButton() {
            return button;
        }

        public boolean isPressed() {
            return pressed;
        }
    }

    @Subscribe
    public static class ClientTickListener {
        @Subscribe
        public void onTick(ClientTickEvent event) {
            if (FoxoCraft.moduleManager != null) {
                FoxoCraft.moduleManager.updateModules();
            }
        }
    }

    @Subscribe
    public static class RenderListener {
        @Subscribe
        public void onRender(RenderEvent event) {
            if (FoxoCraft.hudManager != null) {
                FoxoCraft.hudManager.render(event.getPartialTicks());
            }
        }
    }

    @Subscribe
    public static class KeyInputListener {
        @Subscribe
        public void onKeyInput(KeyInputEvent event) {
            if (FoxoCraft.moduleManager != null) {
                for (var module : FoxoCraft.moduleManager.getAllModules()) {
                    if (module.getKeyCode() == event.getKey()) {
                        module.toggle();
                    }
                }
            }
        }
    }

    @Subscribe
    public static class MouseInputListener {
        @Subscribe
        public void onMouseInput(MouseInputEvent event) {
        }
    }
}