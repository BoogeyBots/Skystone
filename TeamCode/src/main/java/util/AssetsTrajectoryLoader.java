/*
package util;

import com.acmerobotics.roadrunner.trajectory.TrajectoryConfig;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

/**
 * Set of utilities for loading trajectories from assets (the plugin save location).
 */
/*
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.config.TrajectoryConfig;

import java.io.IOException;

public class AssetsTrajectoryLoader {
    private static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());

    static {
        MAPPER.registerModule(new KotlinModule());
    }

    /**
     * Loads a trajectory config with the given name.
     */
/*
    public static TrajectoryConfig loadConfig(String name) throws IOException {
        InputStream inputStream = AppUtil.getDefContext().getAssets().open("trajectory/" + name + ".yaml");
        return MAPPER.readValue(inputStream, TrajectoryConfig.class);
    }

    /**
     * Loads a trajectory with the given name.
     * @see #loadConfig(String)
*/
/*
    public static Trajectory load(String name) throws IOException {
        return loadConfig(name).toTrajectory( name);
    }
}
*/
