public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) throws Exception {
    for (int i = 0; i < 3; i++) {
        try {
            robotConnectionManager.getConnection().moveRobotTo(toX, toY);
            break;
        } catch (RobotConnectionException e) {
            if (i == 2)
                throw new RobotConnectionException("Third try failed", e);
        } catch(Exception e) {
            throw new Exception("Unknown exception", e);
            break;
        } finally {
            robotConnectionManager.getConnection().close();
        }
    }
}