// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.execution.runToolbar

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger

interface RTBarAction {
  enum class Type {
    RIGHT_FLEXIBLE,
    RIGHT_STABLE,
    STABLE,
    FLEXIBLE
  }

  fun traceLog(logger: Logger, e: AnActionEvent, additionalInfo: String? = null) {
    /*    if (RunToolbarProcess.logNeeded) {
          logger.info(
            "RunToolbar UPDATE slot: ${e.id()} ${"visible: ${e.presentation.isVisible}, enable: ${e.presentation.isEnabled}"}; ${additionalInfo ?: ""} ")
        }*/
  }

  fun getRightSideType(): Type = Type.STABLE

  fun checkMainSlotVisibility(state: RunToolbarMainSlotState): Boolean
}

internal interface RTRunConfiguration : RTBarAction {
  override fun getRightSideType(): RTBarAction.Type = RTBarAction.Type.STABLE
  fun isStable(): Boolean {
    return getRightSideType() == RTBarAction.Type.STABLE
  }
}
