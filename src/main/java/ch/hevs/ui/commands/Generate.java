package ch.hevs.ui.commands;

import ch.hevs.tools.generateParts.GenerateParts;
import picocli.CommandLine;

/**
 * Picocli informations
 * Generate parts of Shamir secret
 */

@CommandLine.Command(
        name = "GenParts",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Generate the Shamir secret parts",
        optionListHeading = "%nOptions are:%n")
/**
 * This class is managed with picocli and is a tool to interface human inputs with the machine.
 * This tool enables to generate the parts of the secret into files.
 */
public class Generate implements Runnable{
    //*******************************************************************************
    //  A T T R I B U T S
    //*******************************************************************************

    // Generate parts
    @CommandLine.Option(names = { "-g", "--GenPart" }, required = true, description = "Generate parts")
    boolean GenPart = false;

    // #Bytes (modulo)
    @CommandLine.Parameters(paramLabel = "<bytes>",
            description = "Modulo")
    private byte bytes;

    // #Parts
    @CommandLine.Parameters(paramLabel = "<parts>", description = "Number of parts to be generated")
    private int parts;

    // Threshold (k  <= #Parts)
    @CommandLine.Parameters(paramLabel = "<threshold>", description = "threshold")
    private int threshold;

    //*******************************************************************************
    //  M E T H O D S
    //*******************************************************************************
    @Override
    public void run() {
        System.out.println("Generate parts runs");

        GenerateParts gnP = new GenerateParts(bytes, parts, threshold);
    }
}

