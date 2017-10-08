//
// Created by Colten Sullivent on 10/3/17.
//

#include <wx/filedlg.h>
#include "../include/Utility.h"

OS Utility::currentOS;
boost::filesystem::path Utility::execDir;

OS Utility::getCurrentOS() {
  // TODO: Pull info from file if already configured
  if (Utility::currentOS == UNKNOWN) {
    if (BOOST_OS_WINDOWS) {
      Utility::currentOS = WIN;
    } else if (BOOST_OS_MACOS) {
      Utility::currentOS = MACOS;
    } else if (BOOST_OS_LINUX) {
      Utility::currentOS = LINUX;
    } else {
      // TODO: Proper error handling
      Utility::currentOS = UNKNOWN;
    }
  }
  return Utility::currentOS;
}

boost::filesystem::path Utility::getExecDir() {
  if (Utility::execDir.empty()) {
    // TODO: Handle cancel
    // TODO: Try default locations first

    wxFileDialog openFileDialog(nullptr, ("Find Executable"), "", "", "EXE files (*.exe)|*.exe", wxFD_OPEN|wxFD_FILE_MUST_EXIST);
    openFileDialog.ShowModal();
    Utility::execDir = openFileDialog.GetPath();
  }
  return Utility::execDir;
}
