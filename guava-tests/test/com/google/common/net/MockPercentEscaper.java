package com.google.common.net;

public class MockPercentEscaper extends PercentEscaper {
    /**
     * Constructs a percent escaper with the specified safe characters and optional handling of the
     * space character.
     *
     * <p>Not that it is allowed, but not necessarily desirable to specify {@code %} as a safe
     * character. This has the effect of creating an escaper which has no well defined inverse but it
     * can be useful when escaping additional characters.
     *
     * @param safeChars    a non null string specifying additional safe characters for this escaper (the
     *                     ranges 0..9, a..z and A..Z are always safe and should not be specified here)
     * @param plusForSpace true if ASCII space should be escaped to {@code +} rather than {@code %20}
     * @throws IllegalArgumentException if any of the parameters were invalid
     */
    public MockPercentEscaper(String safeChars, boolean plusForSpace) {
        super(safeChars, plusForSpace);
    }

    @Override
    protected String generateSafeCharts(String safeChars, boolean plusForSpace) {
        // Avoid any misunderstandings about the behavior of this escaper
        // TODO(261): - Change the regex rule
        // In here, suppose we change the rule of regex;
        String regex = ".*[0-9].*";
        if (safeChars.matches(regex)) {
            // TODO(261): - Maybe Change the throw message
            throw new IllegalArgumentException(
                    "Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        safeChars += "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        // Avoid ambiguous parameters. Safe characters are never modified so if
        // space is a safe character then setting plusForSpace is meaningless.
        if (plusForSpace && safeChars.contains(" ")) {
            throw new IllegalArgumentException(
                    "plusForSpace cannot be specified when space is a 'safe' character");
        }

        return safeChars;
    }
}
