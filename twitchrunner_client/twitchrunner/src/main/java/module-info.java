/**
 * 
 */
/**
 * 
 */
module twitchrunner {
	opens twitchrunner to com.fasterxml.jackson.databind;
	opens twitchrunner_background to com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires java.desktop;
}