package Exceptions

import org.slf4j.LoggerFactory


class DirectoryNotFoundORPermissionDeniedException extends Exception {
  def logger = LoggerFactory.getLogger(this.getClass)
  logger.error("The directory could not be found OR access was denied to the directory")
}
